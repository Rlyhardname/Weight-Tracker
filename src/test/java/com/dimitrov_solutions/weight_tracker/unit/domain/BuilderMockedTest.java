package com.dimitrov_solutions.weight_tracker.unit.domain;

import underDev.Builder;
import underDev.Chart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BuilderMockedTest {

    @Mock
    Builder<Chart> builder;
    String mockName;

    @BeforeEach
    void init() {
        mockName = "I'm mr mock!";
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void returns_chart_with_name_property() {
        Chart expected = new Chart(mockName);
        when(builder.build()).thenReturn(new Chart(mockName));

        Chart actual = builder.build();
        assertEquals(expected, actual, "Charts should have the name = " + mockName);

        verify(builder, times(1)).build();
    }

    @Test
    void returns_empty_chart_class() {
        Chart expected = new Chart();
        when(builder.build()).thenReturn(new Chart());

        Chart actual = builder.build();
        assertEquals(expected, actual, "Charts should be empty");

        verify(builder, times(1)).build();
    }
}
