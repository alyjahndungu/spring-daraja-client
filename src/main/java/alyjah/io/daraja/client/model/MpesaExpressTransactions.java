package alyjah.io.daraja.client.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "mpesa_express_transactions")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MpesaExpressTransactions extends BaseEntity {

    private String transactionReference;

    private BigDecimal amount;

    private String phoneNumber;

    private String merchantRequestId;

    private String checkoutRequestId;

    private Integer resultCode;

    private String resultDescription;

    private String transactionDate;

    private boolean acknowledged;
}
