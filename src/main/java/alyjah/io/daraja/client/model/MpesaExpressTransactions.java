package alyjah.io.daraja.client.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "mpesa_express_transactions")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MpesaExpressTransactions {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    private String accountReference;

    private String transactionReceiptNumber;

    private BigDecimal amount;

    private String phoneNumber;

    private String businessShortCode;

    private String merchantRequestId;

    private String checkoutRequestId;

    private String resultCode;

    private String resultDescription;

    private String transactionDate;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private Instant createdAt = Instant.now();

    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private   Instant updatedAt = Instant.now();

}
