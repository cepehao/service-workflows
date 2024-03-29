package ru.psu.workflow.serviceworkflows.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.GenericGenerator
import java.util.UUID
import javax.persistence.*

//весь процесс
@Entity
@Table(name = "processes")
class CProcess(
    @Id
    var id: UUID? = null,

    @Column
    var name: String = ""

)
{
    @JsonIgnore
    @OneToMany(mappedBy = "process", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    var processItems = mutableListOf<CProcessItem>()

}