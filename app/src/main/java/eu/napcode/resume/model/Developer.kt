package eu.napcode.resume.model

import eu.napcode.resume.entity.DeveloperEntity

data class Developer(val id: Int,
                     val name: String,
                     val surname : String,
                     val role: String,
                     val summary: String,
                     val mail : String,
                     val home: String,
                     val github: String,
                     val gitlab: String,
                     val playStore: String,
                     val highlights: String) {

    constructor(entity: DeveloperEntity) :
            this(entity.id, entity.name, entity.surname, entity.role, entity.summary,
                    entity.mail, entity.home, entity.github, entity.gitlab, entity.playStore, entity.highlights)
}
