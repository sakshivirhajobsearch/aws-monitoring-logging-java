package com.aws.monitoring.logging;

import software.amazon.awssdk.services.cloudtrail.CloudTrailClient;
import software.amazon.awssdk.services.cloudtrail.model.CloudTrailException;
import software.amazon.awssdk.services.cloudtrail.model.Event;
import software.amazon.awssdk.services.cloudtrail.model.LookupEventsRequest;
import software.amazon.awssdk.services.cloudtrail.model.LookupEventsResponse;

public class CloudTrailExample {

	public static void main(String[] args) {

		CloudTrailClient cloudTrail = CloudTrailClient.create();

		LookupEventsRequest request = LookupEventsRequest.builder().maxResults(5).build();

		try {
			LookupEventsResponse response = cloudTrail.lookupEvents(request);
			System.out.println("Recent CloudTrail Events:");
			for (Event event : response.events()) {
				System.out.printf("Time: %s | Event: %s | User: %s%n", event.eventTime(), event.eventName(),
						event.username());
			}
		} catch (CloudTrailException e) {
			System.err.println("Failed to fetch CloudTrail events: " + e.awsErrorDetails().errorMessage());
		} finally {
			cloudTrail.close();
		}
	}
}
