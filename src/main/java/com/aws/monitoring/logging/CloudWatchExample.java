package com.aws.monitoring.logging;

import software.amazon.awssdk.services.cloudwatch.CloudWatchClient;
import software.amazon.awssdk.services.cloudwatch.model.*;

import java.util.Collections;

public class CloudWatchExample {

	private final CloudWatchClient cloudWatchClient;

	public CloudWatchExample(CloudWatchClient cloudWatchClient) {
		this.cloudWatchClient = cloudWatchClient;
	}

	// ✅ Must be public, no parameters, void return
	public void putCustomMetric() {
		MetricDatum datum = MetricDatum.builder().metricName("ExampleMetric").value(1.0).unit(StandardUnit.COUNT)
				.build();

		PutMetricDataRequest request = PutMetricDataRequest.builder().namespace("ExampleNamespace")
				.metricData(Collections.singletonList(datum)).build();

		cloudWatchClient.putMetricData(request);
	}
}
