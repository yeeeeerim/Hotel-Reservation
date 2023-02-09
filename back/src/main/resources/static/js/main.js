$(function() {

	'use strict';

	// Form

	var contactForm = function() {

		if ($('#contactForm').length > 0 ) {
			$( "#contactForm" ).validate( {
				rules: {
					hotelName: "required",
					cityName: "required",
					tellNumber:"required",
					address:"required",
					hotelImage:"required"
					
				},
				messages: {
					hotelName: "호텔 이름을 입력하세요. ",
					cityName: "시 이름을 입력하세요. ",
					tellNumber:"호텔 전화번호를 입력하세요. ",
					address:"호텔 주소를 입력하세요. ",
					hotelImage: "이미지를 하나이상 첨부하세요 "
				},
				
				
			} );
		}
	};
	contactForm();

});