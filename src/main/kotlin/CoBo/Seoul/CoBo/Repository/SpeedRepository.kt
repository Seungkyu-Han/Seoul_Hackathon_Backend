package CoBo.Seoul.CoBo.Repository

import CoBo.Seoul.CoBo.Data.Entity.Speed
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SpeedRepository: JpaRepository<Speed, Long> {
}