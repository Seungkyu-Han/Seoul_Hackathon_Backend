package CoBo.Seoul.CoBo.Data.Dto.Res

import CoBo.Seoul.CoBo.Data.Entity.Speed
import CoBo.Seoul.CoBo.Data.RegionEnum
import lombok.Data
import java.time.LocalDateTime

@Data
data class SpeedDto(
    var speed:Float,
    var created_at:LocalDateTime,
    var region: RegionEnum,
    var direction: Int
)