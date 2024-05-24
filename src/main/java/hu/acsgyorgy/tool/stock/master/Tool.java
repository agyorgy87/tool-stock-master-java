package hu.acsgyorgy.tool.stock.master;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tool_stock_master")
@Getter
@Setter
public class Tool {

    @Id
    @GeneratedValue
    private int id;

    private int productId;

    private String company;

    private String toolName;

    private int quantity;

    private double price;

}
