package alyjah.io.daraja.client.business;

import alyjah.io.daraja.client.exception.NotFoundException;
import alyjah.io.daraja.client.model.MpesaExpressTransactions;
import alyjah.io.daraja.client.repository.ExpressTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ExpressTransactionServiceImpl implements ExpressTransactionService {
    private final ExpressTransactionRepository expressTransactionRepository;


    @Override
    public void save(String checkoutRequestID, String merchantRequestID, BigDecimal amount, String phoneNumber, String accountReference, String transactionTimestamp) {
       MpesaExpressTransactions transactions = MpesaExpressTransactions.builder()
               .transactionReference(accountReference)
               .merchantRequestId(merchantRequestID)
               .amount(amount)
               .checkoutRequestId(checkoutRequestID)
               .phoneNumber(phoneNumber)
               .transactionDate(transactionTimestamp)
               .build();
       expressTransactionRepository.save(transactions);
    }

    @Override
    public MpesaExpressTransactions update(String checkoutRequestID, String merchantRequestID, int resultCode, String resultDesc) {
        return expressTransactionRepository.findMpesaExpressTransactionsByMerchantRequestIdAndCheckoutRequestId(merchantRequestID, checkoutRequestID)
                .map(mpesaExpressTransactions -> {
                    mpesaExpressTransactions.setCheckoutRequestId(checkoutRequestID);
                    mpesaExpressTransactions.setResultCode(resultCode);
                    mpesaExpressTransactions.setResultDescription(resultDesc);
                    if(resultCode == 0) mpesaExpressTransactions.setAcknowledged(true);
                    return expressTransactionRepository.save(mpesaExpressTransactions);
                })
                .orElseThrow(() -> new NotFoundException("Transaction not found"));
    }




}
