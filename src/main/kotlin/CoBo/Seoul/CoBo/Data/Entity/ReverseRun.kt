package CoBo.Seoul.CoBo.Data.Entity

import CoBo.Seoul.CoBo.Data.RegionEnum
import CoBo.Seoul.CoBo.Data.WeekEnum
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name="reverse_run")
@Data
@NoArgsConstructor
@Builder
data class ReverseRun(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long?,

    var created_at:LocalDateTime,

    @Enumerated(EnumType.STRING)
    var region: RegionEnum,

    var direction:Int,

    @Column(length = 3)
    @Enumerated(EnumType.STRING)
    var day_of_the_week_tag:WeekEnum,

    var time_tag: Short
) {
}