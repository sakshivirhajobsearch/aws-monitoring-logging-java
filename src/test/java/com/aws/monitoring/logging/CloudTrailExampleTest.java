package com.aws.monitoring.logging;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.Test;

import software.amazon.awssdk.services.cloudtrail.CloudTrailClient;
import software.amazon.awssdk.services.cloudtrail.model.Event;
import software.amazon.awssdk.services.cloudtrail.model.LookupEventsRequest;
import software.amazon.awssdk.services.cloudtrail.model.LookupEventsResponse;

public class CloudTrailExampleTest {

	@Test
	void testLookupEventsSuccess() {
		CloudTrailClient mockClient = mock(CloudTrailClient.class);

		Event event = Event.builder().eventName("TestEvent").eventTime(Instant.now()).username("test-user").build();

		LookupEventsResponse response = LookupEventsResponse.builder().events(List.of(event)).build();

		when(mockClient.lookupEvents(any(LookupEventsRequest.class))).thenReturn(response);

		LookupEventsRequest request = LookupEventsRequest.builder().maxResults(1).build();

		LookupEventsResponse actualResponse = mockClient.lookupEvents(request);

		assertEquals(1, actualResponse.events().size());
		assertEquals("TestEvent", actualResponse.events().get(0).eventName());
	}
}
