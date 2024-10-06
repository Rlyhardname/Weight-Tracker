package com.dimitrov_solutions.weight_tracker.unit.utils;

import com.dimitrov_solutions.weight_tracker.utils.UUID7;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class UUID7test {

    @Nested
    public class UUID7WithInitTest {
        List<String> actual;

        @BeforeEach
        void init() {
            actual = List.of("ffb3a515-c202-4e71-bbd5-46365070174d"
                    , "0257de3c-ec5b-4dab-8b31-80c7508cf456"
                    , "just random input"
                    , "423943208432490328432943240328423423820938423049832"
                    , "0257de3c-ec5b-4dab-8b31-80c7508cf456-42dg1");
        }

        @Test
        void uuid_value_not_supported_or_invalid_throws_illegalArgumentException() {

            assertAll(() -> {
                assertThrows(IllegalArgumentException.class, () -> UUID7.isUuidValid(UUID.fromString(actual.get(0))));
                assertThrows(IllegalArgumentException.class, () -> UUID7.isUuidValid(UUID.fromString(actual.get(1))));
                assertThrows(IllegalArgumentException.class, () -> UUID7.isUuidValid(UUID.fromString(actual.get(2))));
                assertThrows(IllegalArgumentException.class, () -> UUID7.isUuidValid(UUID.fromString(actual.get(3))));
                assertThrows(IllegalArgumentException.class, () -> UUID7.isUuidValid(UUID.fromString(actual.get(4))));
            });

        }

        @Test
        void produces_throws_exception_from_local_date_time_to_uuid() {

            assertAll(() -> {
                assertThrows(IllegalArgumentException.class, () -> UUID7.localDtFromUuid7(UUID.fromString(actual.get(0))));
                assertThrows(IllegalArgumentException.class, () -> UUID7.localDtFromUuid7(UUID.fromString(actual.get(1))));
                assertThrows(IllegalArgumentException.class, () -> UUID7.localDtFromUuid7(UUID.fromString(actual.get(2))));
                assertThrows(IllegalArgumentException.class, () -> UUID7.localDtFromUuid7(UUID.fromString(actual.get(3))));
                assertThrows(IllegalArgumentException.class, () -> UUID7.localDtFromUuid7(UUID.fromString(actual.get(4))));
            });
        }
    }

    @RepeatedTest(value = 20, failureThreshold = 1)
    void generates_uuid7_successfully() {
        UUID actual = UUID7.generateUuid7();

        assertTrue(UUID7.isUuidValid(actual));
    }

    @Test
    void produces_local_date_time_from_uuid() {
        UUID uuid = UUID.fromString("01923e16-da66-7966-95b9-25b60e80ccbc");

        assertEquals(LocalDateTime.class, UUID7.localDtFromUuid7(uuid).getClass());
    }

    @Test
    void null_argument_on_is_UUid_Valid_trows_NullPointerException() {
        UUID uuid = null;

        assertThrows(NullPointerException.class, () -> UUID7.isUuidValid(uuid));
    }

    @Test
    void null_argument_on_Ldt_from_Uuid_throws_NullPointerException() {
        UUID uuid = null;

        assertThrows(NullPointerException.class, () -> UUID7.localDtFromUuid7(uuid));
    }


}
