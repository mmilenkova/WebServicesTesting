package api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;
@Getter
@Setter
@Builder
public class Item {
    private Integer id;
    private String name;
    private Float price;
    private String quantity_unit;
    private Integer price_for_quantity;
}
