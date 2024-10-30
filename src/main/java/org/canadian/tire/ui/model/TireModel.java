package org.canadian.tire.ui.model;

import lombok.*;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TireModel {

    private String searchKeyword;
    private String name;
    private String width;
    private String aspectRatio;
    private String diameter;
    private Integer tireCount;

    @Override
    public String toString() {
        return String.format("%s/%s/%s", width, aspectRatio, diameter);
    }
}
