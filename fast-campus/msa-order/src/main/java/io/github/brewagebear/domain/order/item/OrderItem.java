package io.github.brewagebear.domain.order.item;

import com.google.common.collect.Lists;
import io.github.brewagebear.domain.AbstractEntity;
import io.github.brewagebear.common.exception.InvalidParamException;
import io.github.brewagebear.domain.order.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "order_items")
public class OrderItem extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private Integer orderCount;
    private Long partnerId;
    private Long itemId;
    private String itemName;
    private String itemToken;
    private Long itemPrice;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderItem", cascade = CascadeType.PERSIST)
    private List<OrderItemOptionGroup> orderItemOptionGroupList = Lists.newArrayList();

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @Getter
    @AllArgsConstructor
    public enum DeliveryStatus {
        BEFORE_DELIVERY("배송전"),
        DELIVERY_PREPARE("배송준비중"),
        DELIVERING("배송중"),
        COMPLETE_DELIVERY("배송완료");

        private final String description;
    }

    @Builder
    public OrderItem(
            Order order,
            Integer orderCount,
            Long partnerId,
            Long itemId,
            String itemName,
            String itemToken,
            Long itemPrice
    ) {
        if (order == null) throw new InvalidParamException("OrderItemLine.order");
        if (orderCount == null) throw new InvalidParamException("OrderItemLine.orderCount");
        if (partnerId == null) throw new InvalidParamException("OrderItemLine.partnerId");
        if (itemId == null && StringUtils.isEmpty(itemName))
            throw new InvalidParamException("OrderItemLine.itemNo and itemName");
        if (StringUtils.isEmpty(itemToken)) throw new InvalidParamException("OrderItemLine.itemToken");
        if (itemPrice == null) throw new InvalidParamException("OrderItemLine.itemPrice");

        this.order = order;
        this.orderCount = orderCount;
        this.partnerId = partnerId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemToken = itemToken;
        this.itemPrice = itemPrice;
        this.deliveryStatus = DeliveryStatus.BEFORE_DELIVERY;
    }

    public Long calculateTotalAmount() {
        var itemOptionTotalAmount = orderItemOptionGroupList.stream()
                .mapToLong(OrderItemOptionGroup::calculateTotalAmount)
                .sum();
        return (itemPrice + itemOptionTotalAmount) * orderCount;
    }
}
