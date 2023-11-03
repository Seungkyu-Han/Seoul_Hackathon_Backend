package CoBo.Seoul.CoBo.Repository

import CoBo.Seoul.CoBo.Data.Entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository:JpaRepository<User, Int> {
}