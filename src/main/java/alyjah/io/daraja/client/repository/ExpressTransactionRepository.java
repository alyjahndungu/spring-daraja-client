package alyjah.io.daraja.client.repository;

import alyjah.io.daraja.client.model.MpesaExpressTransactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExpressTransactionRepository extends JpaRepository<MpesaExpressTransactions, String> {

    Optional<MpesaExpressTransactions> findMpesaExpressTransactionsByMerchantRequestIdAndCheckoutRequestId(String merchantRequestId, String checkoutRequestId);
}
