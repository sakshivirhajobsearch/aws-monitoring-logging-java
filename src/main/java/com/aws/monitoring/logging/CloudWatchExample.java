package com.aws.monitoring.logging;

import java.time.Instant;

import software.amazon.awssdk.services.cloudwatch.CloudWatchClient;
import software.amazon.awssdk.services.cloudwatch.model.CloudWatchException;
import software.amazon.awssdk.services.cloudwatch.model.MetricDatum;
import software.amazon.awssdk.services.cloudwatch.model.PutMetricDataRequest;
import software.amazon.awssdk.services.cloudwatch.model.StandardUnit;

public class CloudWatchExample {

	public static void main(String[] args) {

		CloudWatchClient cloudWatch = CloudWatchClient.create();

		MetricDatum datum = MetricDatum.builder().metricName("CustomCPUUtilization").unit(StandardUnit.PERCENT)
				.value(60.0).timestamp(Instant.now()).build();

		PutMetricDataRequest request = PutMetricDataRequest.builder().namespace("MyCustomNamespace").metricData(datum)
				.build();

		try {
			cloudWatch.putMetricData(request);
			System.out.println("Metric sent to CloudWatch successfully.");
		} catch (CloudWatchException e) {
			System.err.println("Error sending metric: " + e.awsErrorDetails().errorMessage());
		} finally {
			cloudWatch.close();
		}
	}
}
