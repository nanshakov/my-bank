import com.nanshakov.bank.dto.ExecutionReport
import com.nanshakov.bank.dto.Order

/**
 * @author Nikita Anshakov
 * @version 30.09.2019* @since 30.09.2019
 */
interface BaseOrderProcessor {

    boolean isApplying(Order order)

    ExecutionReport result(Order order)
}