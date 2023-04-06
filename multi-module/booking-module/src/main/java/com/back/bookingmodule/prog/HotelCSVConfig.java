package com.back.bookingmodule.prog;


import com.back.bookingmodule.domain.Hotel;
import com.back.bookingmodule.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.item.support.ListItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.annotation.CreatedBy;

@Configuration
@Slf4j
@EnableBatchProcessing
@RequiredArgsConstructor
public class HotelCSVConfig {


    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private final HotelRepository hotelRepository;

    private final String CSV_PATH = "src/main/resources/hotel-data.csv";

    private final ResourceLoader resourceLoader;

    @Bean
    public Job csvItemWriterJob() throws Exception {

        return this.jobBuilderFactory
                .get("csvItemWriterJob")
                .incrementer(new RunIdIncrementer())
                .start(this.csvItemReaderStep())
                .build();
    }


    /**
     * @apiNote Hotel -> CSV Step
     *
     * */
    @Bean
    @JobScope
    public Step csvItemWriterStep() throws Exception {
        return this.stepBuilderFactory
                .get("csvItemWriterStep")
                .<Hotel,Hotel>chunk(100)
                .reader(this.csvItemReader())
                .writer(this.csvItemWriter())
                .build();
    }
    /**
     * @apiNote CSV -> DB Step
     *
     * */
    @Bean
    @JobScope
    public Step csvItemReaderStep() throws Exception {
        return this.stepBuilderFactory
                .get("csvItemWriterStepFromCSV")
                .<Hotel,Hotel>chunk(200)
                .reader(this.csvItemReaderFromCSV())
                .writer(this.csvItemWriterFromCSV())
                .build();
    }

    private ItemWriter<? super Hotel> csvItemWriterFromCSV() {
        return items -> hotelRepository.saveAll(items);

    }

    private ItemReader<? extends Hotel> csvItemReaderFromCSV() throws Exception {
        DefaultLineMapper<Hotel> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("id","hotelName","cityName","tellNumber","latitude","longitude","writer");
        lineMapper.setLineTokenizer(tokenizer);

        lineMapper.setFieldSetMapper(fieldSet -> Hotel.builder()
                .id(fieldSet.readLong("id"))
                .hotelName(fieldSet.readString("hotelName"))
                .cityName(fieldSet.readString("cityName"))
                .tellNumber(fieldSet.readString("tellNumber"))
                .latitude(fieldSet.readString("latitude"))
                .longitude(fieldSet.readString("longitude"))
                .writer(fieldSet.readString("writer"))
                .build());

        FlatFileItemReader<Hotel> flatFileItemReader = new FlatFileItemReaderBuilder<Hotel>()
                .name("csvItemReaderFromCSV")
                .encoding("UTF-8")
                .resource(resourceLoader.getResource("classpath:hotel-data.csv"))
                .linesToSkip(2)
                .lineMapper(lineMapper)
                .build();
        //클래스 경로에 있는 파일을 읽으려면 classpath 형태로 읽어야함
        flatFileItemReader.afterPropertiesSet();

        return flatFileItemReader;
    }


    /**
     * @apiNote Hotel -> CSV 파일로 저장
     *
     * */
    private ItemWriter<? super Hotel> csvItemWriter() throws Exception {
        BeanWrapperFieldExtractor<Hotel>  fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[]{"id","hotelName","cityName","tellNumber","latitude","longitude","writer"});

        DelimitedLineAggregator<Hotel> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter(",");
        lineAggregator.setFieldExtractor(fieldExtractor);

        FlatFileItemWriter<Hotel> itemWriter = new FlatFileItemWriterBuilder<Hotel>()
                .name("csvFileItemWriter")
                .encoding("UTF-8")
                .resource(new FileSystemResource(CSV_PATH))
                .lineAggregator(lineAggregator)
                .headerCallback(writer -> writer.write("id,호텔이름,도시이름,전화번호,위도,경도,작성자"))
                .append(true)
                .build();

        itemWriter.afterPropertiesSet();
        return itemWriter;
    }


    /**
     * @apiNote Hotel -> Hotel 데이터 읽기
     *
     * */
    private ItemReader<? extends Hotel> csvItemReader() {
        return new ListItemReader<>(hotelRepository.findAll());
    }

}
