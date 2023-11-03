package CoBo.Seoul.CoBo.Data.Dto

import CoBo.Seoul.CoBo.Data.RegionEnum
import lombok.Data
import java.time.LocalDateTime

@Data
class ReverseRunDto(
    var created_at: LocalDateTime,
    var region: RegionEnum,
    var direction: Int
    )