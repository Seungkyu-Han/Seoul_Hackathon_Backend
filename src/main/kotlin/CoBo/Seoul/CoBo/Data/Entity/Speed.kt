package CoBo.Seoul.CoBo.Data.Entity

import CoBo.Seoul.CoBo.Data.RegionEnum
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name="Speed")
@Data
@NoArgsConstructor
data class Speed(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long?,

    var speed:Float,

    var created_at:LocalDateTime,

    @Enumerated(EnumType.STRING)
    var region:RegionEnum,

    var direction:Int,

    @Column(length = 3)
    var day_of_the_week_tag:String,

    var time_tag:Short,

    var overSpeed:Boolean
){
}