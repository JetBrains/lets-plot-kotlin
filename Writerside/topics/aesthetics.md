# Aesthetics

## Color and Fill

Colors and fills of geometries can be specified in the following ways:

- RGB/RGBA (e.g. `"rgb(0, 0, 255)"`, `"rgba(0, 0, 255, 0.5)"`).

- HEX (e.g. `"#0000ff"`, `"#00f"`).

- Blank string (`""`) or aliases: `"blank"`, `"transparent"` for a fully transparent color.

- A name, one of:

  ![Color names](aesthetics_color.png)

- A system color name, one of:

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

- An instance of the `java.awt.Color` class.

## Point Shapes

![Point shapes](aesthetics_shape.png)

## Line Types

### Predefined Patterns

![Predefined line types](aesthetics_linetype.png)

### Custom Patterns

Ways to specify the `linetype`:

- list, defining the pattern of dashes and gaps used to draw the line: `listOf(dash, gap, ...)`;
- pair of offset and pattern: `offset to listOf(dash, gap, ...)`;
- string of an even number (up to eight) of hexadecimal digits which give the lengths in consecutive positions in the string.

![Custom line types](aesthetics_custom_linetype.png)

## Text

### Font Family

Universal font names:

![Font families](aesthetics_font_family.png)

The default font family is `'sans'`.

You can also use the name of any other font installed on your system (e.g. `"Times New Roman"`).

### Font Face

![Font faces](aesthetics_font_face.png)

The default font face is `'plain'`.