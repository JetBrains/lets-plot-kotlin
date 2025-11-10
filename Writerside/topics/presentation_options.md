# Presentation Options

## General Functions

[`theme()`](%api_theme%/theme/index.html),
[`ggtitle()`](%api_label%/ggtitle.html),
[`ggsize()`](%api_lets_plot%/ggsize.html),
[`xlab()`](%api_label%/xlab.html),
[`ylab()`](%api_label%/ylab.html),
[`labs()`](%api_label%/labs.html),
[`guideLegend()`](%api_scale%/guide-legend.html),
[`guideColorbar()`](%api_scale%/guide-colorbar.html),
[`guides()`](%api_scale%/guides.html)

### Examples

- [Themes overview](%nb-themes%)
- [Default theme](%nb-default_theme%)
- [Justification for text elements](%nb-hjust_vjust%)
- [Margins](%nb-margins%)
- [`margin` parameter of `elementText()`](%nb-text_margins%)
- [Panel border](%nb-panel_border%)
- [Plot title, subtitle and caption](%nb-title_subtitle_caption%)
- [Theme colors for geometries](%nb-geom_theme_colors%)
- [Customize legend appearance](%nb-legend_theme%)
- [Tooltip customization](%nb-tooltip_config%)
- [Set font faces](%nb-set_font_faces%)
- [Set font size and family](%nb-font_size_and_family%)
- [Rotation of axis labels](%nb-axis_text_angle%)
- [Exponent format in Lets-Plot](%nb-superscript_exponent%)
- [Annotation labels on pie-chart](%nb-annotations_for_pie%)

## Predefined Themes

[`minimal2`](%api_theme%/theme-minimal2.html),
[`bw`](%api_theme%/theme-b-w.html),
[`grey`](%api_theme%/theme-grey.html) (or [`gray`](%api_theme%/theme-gray.html)),
[`classic`](%api_theme%/theme-classic.html),
[`light`](%api_theme%/theme-light.html),
[`minimal`](%api_theme%/theme-minimal.html),
[`void`](%api_theme%/theme-void.html),
[`none`](%api_theme%/theme-none.html)

<a href="%nb-complete_themes%">
    <img alt="All predefined themes" src="complete_themes.png" style="block"/>
</a>

### Predefined Themes Examples

- [Default theme](%nb-default_theme%)
- [Themes overview](%nb-themes%)
- [Theme flavors](%nb-theme_flavors%)
- [Margins](%nb-margins%)
- [`margin` parameter of `elementText()`](%nb-text_margins%)
- [Theme colors for geometries](%nb-geom_theme_colors%)
- [Axis position](%nb-axis_position%)
- [Exponent format in Lets-Plot](%nb-superscript_exponent%)

## Color Schemes (Flavors)

[`darcula`](%api_theme%/flavor-darcula.html),
[`solarized light`](%api_theme%/flavor-solarized-light.html),
[`solarized dark`](%api_theme%/flavor-solarized-dark.html),
[`high contrast light`](%api_theme%/flavor-high-contrast-light.html),
[`high contrast dark`](%api_theme%/flavor-high-contrast-dark.html),
[`standard`](%api_theme%/flavor-standard.html)

<a href="%nb-geom_theme_colors%">
    <img alt="Theme colors for geometries" src="flavors.png" style="block"/>
</a>

### Flavors Examples

- [Theme flavors](%nb-theme_flavors%)
- [Themes overview](%nb-themes%)
- [Theme colors for geometries](%nb-geom_theme_colors%)
- [Annotation labels on pie-chart](%nb-annotations_for_pie%)

## Plot Layout Diagrams

These diagrams illustrate layout options and their spatial relationships within plot components.

Option names on the diagrams (e.g., `axisTextSpacingX`) correspond to [`theme()`](%api_theme%/theme/index.html) function arguments.

**Simple options** accept numeric values directly, e.g. `theme(axisTextSpacingX = 10)`.

**Composite options** shown as `axisTitleX: margin` accept [`elementText()`](%api_theme%/element-text.html) or [`elementRect()`](%api_theme%/element-rect.html) function results, e.g. `theme(axisTitleX = elementText(margin = listOf(5, 5)))`.

### Plot Panel Layout

<img src="plot_layout_scheme.png" alt="Plot layout scheme and theme parameters" style="block"/>

### Legend Box Layout

<img src="theme_legend_scheme.png" alt="Theme legend scheme" style="block"/>