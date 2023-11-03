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

    var overSpeed:Boolean
){
}