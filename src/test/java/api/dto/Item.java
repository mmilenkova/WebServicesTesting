package api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;
@Getter
@Setter
@AllArgsConstructor
public class Item {
    private String name;
    private Float price;
    private String quantity_unit;
    private Integer price_for_quantity;
}
