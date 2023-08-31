package dev.amaro.atairu

import dev.amaro.atairu.schema.MosaicPalette
import dev.amaro.atairu.schema.MosaicSizes
import dev.amaro.atairu.schema.MosaicTextStyle
import dev.amaro.atairu.schema.MosaicTheme
import dev.amaro.atairu.schema.MosaicTypography
import dev.amaro.atairu.schema.toColor
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    val ThemeSample = """
     {
    "id": 1,
    "name": "Blue",
    "typography": {
      "fontSizes": {
        "units": {
          "ios": "pt",
          "web": "pt",
          "android": "sp"
        },
        "specs": {
          "smaller": {
            "ios": 8,
            "web": 8,
            "android": 10
          },
          "small": {
            "ios": 10,
            "web": 10,
            "android": 12
          },
          "regular": {
            "ios": 12,
            "web": 12,
            "android": 14
          },
          "big": {
            "ios": 14,
            "web": 14,
            "android": 16
          },
          "bigger": {
            "ios": 16,
            "web": 16,
            "android": 18
          }
        }
      },
      "header": {
        "bold": true,
        "color": "Primary",
        "fontSize": "bigger"
      },
      "subHeader": {
        "bold": true,
        "color": "Primary",
        "fontSize": "big"
      },
      "text": {
        "color": "#333333",
        "fontSize": "regular"
      }
    },
    "palette": {
      "primary": "#333399",
      "onPrimary": "#FFFFFF",
      "background": "#FFFFFF",
      "onBackground": "#3366CC",
      "surface": "#EEEEFF",
      "onSurface": "#333399",
      "accent": "#D9E535"
    },
    "dimensions": {
      "units": {
        "ios": "pt",
        "web": "em",
        "android": "dp"
      },
      "specs": {
        "smaller": {
          "ios": 2,
          "web": 1,
          "android": 4
        },
        "small": {
          "ios": 4,
          "web": 2,
          "android": 6
        },
        "regular": {
          "ios": 6,
          "web": 2.5,
          "android": 8
        },
        "big": {
          "ios": 12,
          "web": 4,
          "android": 16
        },
        "bigger": {
          "ios": 16,
          "web": 5,
          "android": 20
        }
      }
    }
  }   
        """


    @Test
    fun parseDimensionSizes() {
        val theme = MosaicTheme.from(ThemeSample)
        assertEquals(MosaicSizes(4, 6, 8, 16, 20), theme.sizes)
    }

    @Test
    fun parseFontSizes() {
        val theme = MosaicTheme.from(ThemeSample)
        assertEquals(MosaicSizes(10, 12, 14, 16, 18), theme.fontSizes)
    }

    @Test
    fun parsePalette() {
        val theme = MosaicTheme.from(ThemeSample)
        assertEquals(
            MosaicPalette(
                primary = "#333399".toColor(),
                surface = "#EEEEFF".toColor(),
                background = "#FFFFFF".toColor()
            ), theme.palette
        )
    }

    @Test
    fun parseTypography() {
        val theme = MosaicTheme.from(ThemeSample)
        assertEquals(
            MosaicTypography(
                header = MosaicTextStyle("#333399".toColor(), 18, true),
                subHeader = MosaicTextStyle("#333399".toColor(), 16, true),
                text = MosaicTextStyle("#333333".toColor(), 14, false),
            ), theme.typography
        )
    }

}







