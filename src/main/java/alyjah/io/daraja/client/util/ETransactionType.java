package alyjah.io.daraja.client.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ETransactionType {
      CUSTOMER_PAYBILL_ONLINE("CustomerPayBillOnline");

      final  String type;

}
