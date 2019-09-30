import com.nanshakov.bank.dto.ExecutionReport
import com.nanshakov.bank.dto.Order
import com.nanshakov.bank.utils.OrderType

/**
 * @author Nikita Anshakov
 * @version 30.09.2019* @since 30.09.2019
 */
class MIT implements BaseOrderProcessor {

    @Override
    boolean isApplying(Order order) {
        if (order.orderType == OrderType.Market)
            return true
        return false
    }

    @Override
    ExecutionReport result(Order order) {
        return new ExecutionReport(true, order.getAmount())
    }
}
