# Aesthetics

## Point Shapes

![Point shapes](aesthetics_shape.png)

See [example notebook](%nb-aesthetics-point_shapes%).

## Line Types

### Predefined Patterns

![Predefined line types](aesthetics_linetype.png)

See [example notebook](%nb-aesthetics-line_types-predefined_patterns%).

### Custom Patterns

Ways to specify the `linetype`:

- list, defining the pattern of dashes and gaps used to draw the line: `listOf(dash, gap, ...)`;
- pair of offset and pattern: `offset to listOf(dash, gap, ...)`;
- string of an even number (up to eight) of hexadecimal digits which give the lengths in consecutive positions in the string.

![Custom line types](aesthetics_custom_linetype.png)

See [example notebook](%nb-aesthetics-line_types-custom_patterns%).

## Text

### Font Family

Universal font names:

![Font families](aesthetics_font_family.png)

The default font family is `'sans'`.

You can also use the name of any other font installed on your system (e.g. `"Times New Roman"`).

See [example notebook](%nb-aesthetics-text_style-font_family%).

### Font Face

![Font faces](aesthetics_font_face.png)

The default font face is `'plain'`.

See [example notebook](%nb-aesthetics-text_style-font_face%).

## Color and Fill

Colors can be specified using <a href="named_colors.md">named colors</a>,
theme-dependent system colors (`"pen"`, `"paper"`, `"brush"`), RGB/RGBA strings, HEX values,
or `color(...)`. Named colors are case-insensitive;
hyphens and underscores are ignored, and `grey` is treated the same as `gray`
(`"dark_orange"`, `"light-blue"`, and `"DARK-GREY"` are valid).
In addition to the named colors listed in the <a href="named_colors.md">Named colors reference</a>,
grayscale names from `"gray0"` to `"gray100"` are supported.
Transparency can be included directly in the color value by using an alpha-enabled format
or by appending opacity to a named color, for example `"steelblue / 0.35"`.

<table>
    <tr>
        <td>Type</td>
        <td>Format</td>
        <td>Example</td>
    </tr>
    <tr>
        <td>Named color</td>
        <td>name</td>
        <td>"steelblue"</td>
    </tr>
    <tr>
        <td>Named color with opacity</td>
        <td>name / a</td>
        <td>"steelblue / 0.35"</td>
    </tr>
    <tr>
        <td>System color</td>
        <td>pen, brush, paper</td>
        <td>"pen"</td>
    </tr>
    <tr>
        <td>RGB</td>
        <td>rgb(r, g, b)</td>
        <td>"rgb(70, 130, 180)"</td>
    </tr>
    <tr>
        <td>RGBA</td>
        <td>rgba(r, g, b, a)</td>
        <td>"rgba(70, 130, 180, 0.35)"</td>
    </tr>
    <tr>
        <td>Color function</td>
        <td>color(r, g, b)</td>
        <td>"color(70, 130, 180)"</td>
    </tr>
    <tr>
        <td>Color function with opacity</td>
        <td>color(r, g, b, a)</td>
        <td>"color(70, 130, 180, 0.35)"</td>
    </tr>
    <tr>
        <td>HEX RGB</td>
        <td>#RRGGBB, #RGB</td>
        <td>"#4682B4", "#48B"</td>
    </tr>
    <tr>
        <td>HEX RGBA</td>
        <td>#RRGGBBAA, #RGBA</td>
        <td>"#4682B459", "#48B6"</td>
    </tr>
    <tr>
        <td>An instance of the java.awt.Color class</td>
        <td>Color.NAME, Color(r, g, b), …</td>
        <td>Color.MAGENTA</td>
    </tr>
    <tr>
        <td>Transparent</td>
        <td>transparent, blank, empty string</td>
        <td>"transparent"</td>
    </tr>
</table>

For opacity values, `0` means fully transparent and `1` means fully opaque;
percentage values such as `"steelblue/35%"` are not supported. See also an [example](%nb-color_alpha%).

System colors `"pen"`, `"paper"`, and `"brush"` can be used when you want a color to adapt to the active theme or <a href="presentation_options.md#color-schemes-flavors">flavor</a>.

<list columns="3">
    <li>
        <img alt="Color 'pen'" src="aesthetics_color_pen.png"/>
    </li>
    <li>
        <img alt="Color 'brush'" src="aesthetics_color_brush.png"/>
    </li>
    <li>
        <img alt="Color 'paper'" src="aesthetics_color_paper.png"/>
    </li>
</list>

See [example notebook](%nb-aesthetics-system_colors%).