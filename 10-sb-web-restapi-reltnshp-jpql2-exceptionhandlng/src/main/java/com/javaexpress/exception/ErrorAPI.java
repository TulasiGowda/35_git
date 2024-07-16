package com.javaexpress.exception;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorAPI {

	private Integer statusCode;
	private String title;
	private String status;
	private String details;
	private LocalDateTime localDateTime;

}
