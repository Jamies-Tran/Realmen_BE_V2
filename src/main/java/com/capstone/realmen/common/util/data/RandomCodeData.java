package com.capstone.realmen.common.util.data;

import java.util.Random;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record RandomCodeData(String random) {
    public static RandomCodeData generate() {
        StringBuilder random = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            random.append(new Random().nextInt(9));
        }
        return RandomCodeData.builder().random(random.toString()).build();
    }
}
