package com.guillermomuntaner.adoptapuppy.model

import androidx.annotation.DrawableRes
import com.guillermomuntaner.adoptapuppy.R

data class Puppy(
    val id: Int,
    @DrawableRes val drawableRes: Int,
    val name: String,
    val age: String,
    val race: String,
    val gender: Gender,
    val description: String,
)

enum class Gender {
    Female, Male
}

val samplePuppies = listOf(
    Puppy(1, R.drawable.berkay_gumustekin, "Bella", "2 months old", "Labrador", Gender.Female, "Bella is a kind, pleasant and friendly Labrador. She loves kids and is looking for a family."),
    Puppy(2, R.drawable.bill_stephan, "Toby", "6 months old", "Golden retriever", Gender.Male, "Toby is a good guy. Despite his young age he is already very well trained and obedient."),
    Puppy(3, R.drawable.erin_minuskin, "Lola", "5 months old", "Pomeranian", Gender.Female, "She is so chic. Requires frequent hair cuts though."),
    Puppy(4, R.drawable.mathis_jrdl, "Max", "3 months old", "Australian Shepherd", Gender.Male, "Beautiful australian shepherd puppy with heterochromia. It requires lot of exercise on a daily basis, so the ideal match is either a very active family or to be used for actual sheepherding."),
    Puppy(5, R.drawable.flouffy_wap, "Daisy", "3 months old", "Cavalier King Charles spaniel", Gender.Female, "A royal dog looking for a royal family."),
    //Puppy(6, R.drawable.david_clarke, "david clarke", "6 months old"),
    // Puppy(7, R.drawable.hannah_grace, "hannah_grace", "Baby · Labrador"),
    // Puppy(8, R.drawable.ignacio_r, "ignacio_r", "Baby · Labrador"),
    // Puppy(9, R.drawable.michael_kucharski, "michael_kucharski", "Baby · Labrador"),
    // Puppy(10, R.drawable.stephanie_cook, "stephanie_cook", "Baby · Labrador"),
    // Puppy(11, R.drawable.t_r_photography, "t_r_photography", "Baby · Labrador")
)