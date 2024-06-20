package com.fernando.fernando_ecommerce_api.projections;

import java.util.List;
import com.fernando.fernando_ecommerce_api.models.Order;
import lombok.*;

@Getter
@Setter
public class ClientOrdersProjection {
    private List<Order> orders;
}