package CoBo.Seoul.CoBo.Data.Entity

import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*

@Entity
@Table(name="user")
@Data
@NoArgsConstructor
data class User(
    @Id
    var id:Long,

    @Column(length = 30)
    var email: String,

    @Column(length = 5)
    var name:String,

    var alarm:Boolean
) {
}