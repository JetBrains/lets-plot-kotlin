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

Colors and fills of geometries can be specified in the following ways:

- **RGB**/**RGBA** - e.g. `"rgb(0, 0, 255)"`, `"rgba(0, 0, 255, 0.5)"`.

- **HEX** - e.g. `"#0077ff"`, `"#07f"`.

- **Transparent** - an empty string (`""`) or the aliases `"blank"` and `"transparent"` for a fully transparent color.

- **Named colors** from <a href="named_colors.md">a predefined list of color names</a>.

- **System colors** depending on the current theme <a href="presentation_options.md#color-schemes-flavors">flavor</a>, one of:

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

See [example notebook](%nb-aesthetics-system_colors%).