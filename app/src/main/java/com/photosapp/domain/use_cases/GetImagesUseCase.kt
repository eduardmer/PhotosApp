package com.photosapp.domain.use_cases

import com.photosapp.domain.repository.Repository
import javax.inject.Inject

class GetImagesUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke() = repository.getImages()

}