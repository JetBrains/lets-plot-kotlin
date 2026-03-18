# Annotating Charts

Lets-Plot provides several ways to annotate charts:

- **Label annotations for geometry layers.**
  Some geoms (such as pie, bar, and crossbar) support built-in text labels via the
  `labels` parameter. Pass the result of the [`layerLabels()`](%api_annotations%/layer-labels/index.html)
  call to configure the label content and layout.

  [Learn more (reference notebook)](%nbp-annotations%).

- **Annotations for** [`geomSmooth()`](%api_geom%/geom-smooth/index.html).
  Use the `labels` parameter together with [`smoothLabels()`](%api_annotations%/smooth-labels/index.html)
  to display statistics computed by the `smooth` stat (for example, R², adjusted R²,
  and a fitted model equation). `smoothLabels` extends `layerLabels`, so formatting and text
  template helpers work the same way.

  [Learn more (reference notebook)](%nb-smooth_summary%).

- **Bracket annotations.**
  Use [`geomBracket()`](%api_geom%/geom-bracket/index.html) to add labeled brackets highlighting
  relationships between categories or marking an interval.
  Use [`geomBracketDodge()`](%api_geom%/geom-bracket-dodge/index.html) to draw brackets that connect
  *dodged* groups within each category (e.g., comparisons inside grouped boxplots/bars).

  [Learn more (reference notebook)](%nb-geom_bracket%).

See also the [formatting reference](formats.md) to learn how to format numeric and date-time values in annotations.

## Examples

- [Pie chart](%nb-geom_pie%)
- [Factor levels](%nb-factor_levels%)
- [Theme colors for geometries](%nb-geom_theme_colors%)