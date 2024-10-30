package org.canadian.tire.ui.data;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.canadian.tire.ui.model.TireModel;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TireModels {

    public static TireModel getMichelinTireModel() {
        return TireModel.builder()
                .searchKeyword("michelin tires")
                .name("Michelin CrossClimate® 2 All Weather Tire For Passenger and CUV")
                .width("245")
                .aspectRatio("50")
                .diameter("19")
                .tireCount(4)
                .build();
    }
}
