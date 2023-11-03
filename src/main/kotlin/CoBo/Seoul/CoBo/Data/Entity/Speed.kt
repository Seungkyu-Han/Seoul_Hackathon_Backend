package CoBo.Seoul.CoBo.Data.Entity

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

    var region:Float,

    var direction:Short,

    @Column(length = 3)
    var day_of_the_week_tag:String,

    var time_tag:Short,

    var overSpeed:Boolean
){
}