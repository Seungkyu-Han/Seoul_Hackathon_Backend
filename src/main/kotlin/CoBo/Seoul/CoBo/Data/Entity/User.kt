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
    var id:Int,

    @Column(length = 30)
    var email: String,

    var alarm:Boolean,

    var refreshToken:String
) {
}