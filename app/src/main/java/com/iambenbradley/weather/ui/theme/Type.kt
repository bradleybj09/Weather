package com.iambenbradley.weather.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.iambenbradley.weather.R

val TextSizeDegrees = 70.sp
val TextSizeCity = 30.sp
val TextSizeDetailHeader = 12.sp
val TextSizeDetailContent = 15.sp
val TextSizeResultCity = 20.sp
val TextSizeResultDegrees = 60.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    // DEGREES
    displayLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = TextSizeDegrees,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        color = PrimaryText,
    ),
    // CITY
    displayMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = TextSizeCity,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        color = PrimaryText,
    ),
    // SEARCH INSTRUCTION
    displaySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = TextSizeDetailContent,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        color = PrimaryText,
    ),
    // RESULT - DEGREES
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = TextSizeResultDegrees,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        color = PrimaryText,
    ),
    // RESULT - CITY
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = TextSizeResultCity,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        color = PrimaryText,
    ),
    // DETAIL HEADER
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = TextSizeDetailHeader,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        color = DetailHeaderText,
    ),
    // DETAIL CONTENT
    labelMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = TextSizeDetailContent,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        color = DetailContentText,
    ),

    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

