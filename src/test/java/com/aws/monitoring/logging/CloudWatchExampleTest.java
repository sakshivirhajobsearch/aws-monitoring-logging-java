package com.aws.monitoring.logging;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import software.amazon.awssdk.services.cloudwatch.CloudWatchClient;
import software.amazon.awssdk.services.cloudwatch.model.PutMetricDataRequest;
import software.amazon.awssdk.services.cloudwatch.model.PutMetricDataResponse;

public class CloudWatchExampleTest {

	@Test
	public void testPutMetricDataSuccess() {
		CloudWatchClient mockClient = mock(CloudWatchClient.class);

		PutMetricDataResponse mockResponse = PutMetricDataResponse.builder().build();
		when(mockClient.putMetricData(any(PutMetricDataRequest.class))).thenReturn(mockResponse);

		CloudWatchExample example = new CloudWatchExample(mockClient);

		example.putCustomMetric();

		verify(mockClient, times(1)).putMetricData(any(PutMetricDataRequest.class));
	}

	@Test
	public void testPutMetricDataThrowsException() {
		CloudWatchClient mockClient = mock(CloudWatchClient.class);

		when(mockClient.putMetricData(any(PutMetricDataRequest.class)))
				.thenThrow(new RuntimeException("Simulated error"));

		CloudWatchExample example = new CloudWatchExample(mockClient);

		assertThrows(RuntimeException.class, example::putCustomMetric);
	}
}
