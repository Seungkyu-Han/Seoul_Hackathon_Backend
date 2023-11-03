package CoBo.Seoul.CoBo.Repository

import CoBo.Seoul.CoBo.Data.Entity.ReverseRun
import CoBo.Seoul.CoBo.Data.RegionEnum
import CoBo.Seoul.CoBo.Data.WeekEnum
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface ReverseRunRepository:JpaRepository<ReverseRun, Long>{

    @Query("SELECT COUNT(r) FROM ReverseRun r " +
            "WHERE r.day_of_the_week_tag = :weekDay AND r.created_at >= :week " +
            "GROUP BY r.day_of_the_week_tag")
    fun countReverseRunWeekTag(week: LocalDateTime, weekDay: WeekEnum): Int?

    @Query(
        "SELECT COUNT(r) FROM ReverseRun r " +
        "WHERE r.time_tag = :time AND r.created_at >= :week " +
        "GROUP BY r.time_tag"
    )
    fun countReverseRunTimeTag(week: LocalDateTime?, time: Short): Int?

    @Query("SELECT COUNT(r) FROM ReverseRun r " +
            "WHERE r.created_at >= :week and r.region = :regionEnum " +
            "GROUP BY r.region")
    fun countReverseRunRegion(week: LocalDateTime?, regionEnum: RegionEnum): Int?
}