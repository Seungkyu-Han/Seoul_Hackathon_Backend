package CoBo.Seoul.Data.Entity

import CoBo.Seoul.Data.RegionEnum
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name="reverse_run")
@Data
@NoArgsConstructor
class ReverseRun(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long,

    var created_at:LocalDateTime,

    var region:RegionEnum,

    var direction:Int,

    var day_of_the_week_tag:Short,

    var time_tag: Short
) {
}