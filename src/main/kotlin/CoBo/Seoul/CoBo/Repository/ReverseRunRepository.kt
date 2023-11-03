package CoBo.Seoul.CoBo.Repository

import CoBo.Seoul.CoBo.Data.Entity.ReverseRun
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReverseRunRepository:JpaRepository<ReverseRun, Long>{
}