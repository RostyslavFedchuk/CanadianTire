package org.canadian.tire.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DropdownElement {

    TIRE_WIDTH("Section Width"),
    ASPECT_RATIO("Aspect Ratio"),
    DIAMETER("Diameter");

    private final String name;
}
