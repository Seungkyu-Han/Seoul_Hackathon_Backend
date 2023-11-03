package CoBo.Seoul.CoBo.Repository

import CoBo.Seoul.CoBo.Data.Entity.Speed
import CoBo.Seoul.CoBo.Data.RegionEnum
import CoBo.Seoul.CoBo.Data.WeekEnum
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface SpeedRepository: JpaRepository<Speed, Long> {

    fun getAllByOverSpeedIsTrue(pageRequest: PageRequest):ArrayList<Speed>

    @Query("SELECT COUNT(s) FROM Speed s " +
            "WHERE s.created_at >= :week AND s.day_of_the_week_tag = :week_tag")
    fun countSpeedWeekTag(week: LocalDateTime, week_tag:WeekEnum):Int

    @Query("SELECT COUNT(s) FROM Speed s " +
            "WHERE s.created_at >= :week AND s.day_of_the_week_tag = :week_tag AND s.overSpeed = true")
    fun countOverSpeedWeekTag(week: LocalDateTime, week_tag:WeekEnum):Int

    @Query("SELECT COUNT(s) FROM Speed s " +
            "WHERE s.created_at >= :week AND s.time_tag = :time")
    fun countSpeedTimeTag(week: LocalDateTime, time: Short):Int

    @Query(
        "SELECT COUNT(s) FROM Speed s " +
        "WHERE s.created_at >= :week AND s.time_tag = :time AND s.overSpeed = true"
    )
    fun countOverSpeedTimeTag(week: LocalDateTime, time: Short):Int

    @Query(
        "SELECT COUNT(s) FROM Speed s " +
                "WHERE s.created_at >= :week AND s.region = :regionEnum " +
                "GROUP BY s.region"
    )
    fun countSpeedRegion(week: LocalDateTime?, regionEnum: RegionEnum): Int?

    @Query("SELECT COUNT(s) FROM Speed s " +
            "WHERE s.created_at >= :week AND s.region = :regionEnum AND s.overSpeed = true " +
            "GROUP BY s.region")
    fun countOverSpeedWeekRegion(week: LocalDateTime?, regionEnum: RegionEnum):Int?
}