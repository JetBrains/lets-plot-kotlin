# Formatting

Formatting provides the ability to do complex variable substitutions and value formatting.

## Number Format

The numeric format strings are used to format common numeric types. The general form of a format specifier is:

```
[​[fill]align][sign][symbol][0][width][,][.precision][~][type]
```

- *`fill`* - can be any character, defaults to a space if omitted. The presence of a fill character is signaled by the
  `*align*` character following it, which must be one of the alignment options.

- *`align`* - the various alignment options is as follows:

    - `>` - forces the field to be right-aligned within the available space (default behavior);
    - `<` - forces the field to be left-aligned within the available space;
    - `^` - forces the field to be centered within the available space;
    - `=` - like `>`, but with any sign and symbol to the left of any padding.

- *`sign`* can be:

    - `-` - nothing for zero or positive and a minus sign for negative (default behavior);
    - `+` - a plus sign for zero or positive and a minus sign for negative;
    - ` ` (space) - a space for zero or positive and a minus sign for negative.

- *`symbol`* can be:

    - `$` - apply currency symbols per the locale definition;
    - `#` - for binary, octal, or hexadecimal notation, prefix by `0b`, `0o`, or `0x`, respectively.

- *`zero`* (`0`) option enables zero-padding; this implicitly sets *fill* to `0` and *align* to `=`.

- *`width`* defines the minimum field width; if not specified, then the width will be determined by the content.

- *`comma`* (`,`) option enables the use of a group separator, such as a comma for thousands.

- *`precision`* depending on the *`type`*, the *`precision`* either indicates the number of digits that follow the
  decimal point (types `f` and `%`), or the number of significant digits (types `​`, `e`, `g`, `r`, `s` and `p`).
  If the precision is not specified, it defaults to 6 for all types except `​` (none), which defaults to 12.
  Precision is ignored for integer formats (types `b`, `o`, `d`, `x`, `X` and `c`).

- *`~`* trims insignificant trailing zeros across all format types.

- *`type`* determines how the data should be presented:

  - `e` - exponent notation;
  - `f` - fixed point notation;
  - `g` - either decimal or exponent notation, rounded to significant digits;
  - `s` - decimal notation followed by SI prefix symbol, rounded to significant digits;
  - `%` - multiply by 100, and then decimal notation with a percent sign;
  - `b` - binary notation, rounded to integer;
  - `o` - octal notation, rounded to integer;
  - `d` - decimal notation, rounded to integer;
  - `x` - hexadecimal notation, using lower-case letters, rounded to integer;
  - `X` - hexadecimal notation, using upper-case letters, rounded to integer;
  - `c` - simple toString.

  The following prefix symbols are supported for `s` type:

  - `y` - yocto, 10⁻²⁴
  - `z` - zepto, 10⁻²¹
  - `a` - atto, 10⁻¹⁸
  - `f` - femto, 10⁻¹⁵
  - `p` - pico, 10⁻¹²
  - `n` - nano, 10⁻⁹
  - `µ` - micro, 10⁻⁶
  - `m` - milli, 10⁻³
  - `​` (none) - 10⁰
  - `k` - kilo, 10³
  - `M` - mega, 10⁶
  - `G` - giga, 10⁹
  - `T` - tera, 10¹²
  - `P` - peta, 10¹⁵
  - `E` - exa, 10¹⁸
  - `Z` - zetta, 10²¹
  - `Y` - yotta, 10²⁴

<note>
  These formatting rules are compatible with formatting from the d3 library, so you can find out more about the options listed above by visiting <a href="https://d3js.org/d3-format">this page</a>.
</note>

### Number Format Examples

Let's format the number `1024`:

```
010d      -->  "0000001024"
_<10d     -->  "1024______"
_=10d     -->  "______1024"
_=+10d    -->  "+_____1024"
_^11.0%   -->  "__102400%__"
_^11,.0%  -->  "_102,400%__"
+010,d    -->  "+00,001,024"
.1f       -->  "1024.0"
+.3f      -->  "+1024.000"
b         -->  "10000000000"
#b        -->  "0b10000000000"
o         -->  "2000"
e         -->  "1.024000e+3"
~e        -->  "1.024e+3"
s         -->  "1.02400k"
020,s     -->  "0,000,000,001.02400k"
020.0%    -->  "0000000000000102400%"
```

Some other examples:

```
format   number        result
.1f      0.42          "0.4"
0,.1f    1234567.89    "1,234,567.9"
+$,.2f   1e4           "+$10,000.00"
+$,.2~f  1e4           "+$10,000"
~g       0.0000042     "0.0000042"
~g       0.00000042    "4.2e-7"
~g       420000        "420000"
~g       4200000       "4.2e+6"
,.2g     -4231         "-4,2e+3"
,.6g     -4231         "-4,231.00"
,.6~g    -4231         "-4,231"
```

See more examples <a href="https://observablehq.com/@d3/d3-format">here</a>.

## String Template

The number format can be used in a template to create a string with variable substitution.
The string template contains “replacement fields” surrounded by curly braces `{}`.
Anything that is not contained in braces is considered literal text, which is copied unchanged to the result string.
If you need to include a brace character in the literal text, it can be escaped by doubling: {{ and }}.
This approach is used in functions [`layerTooltips()`](%api_tooltips%/layer-tooltips/index.html) and [`layerLabels()`](%api_annotations%/layer-labels/index.html)
to customize the content of tooltips and annotations.

See: [Tooltip Customization in Lets-Plot](tooltips.md) and [Annotating Charts in Lets-Plot](annotations.md).

## Date and Time Format

Provides formats for date and time values.

The list of supported directives to format date/time values:

- `%a` - weekday as an abbreviated name (Sun, Mon, …, Sat);
- `%A` - weekday as a full name (Sunday, Monday, …, Saturday)
- `%b` - month as an abbreviated name (Jan, Feb, …, Dec);
- `%B` - month as a full name (January, February, …, December);
- `%d` - day of the month as a zero-padded decimal number (01, 02, …, 31);
- `%e` - day of the month as a decimal number (1, 2, …, 31);
- `%j` - day of the year as a zero-padded decimal number (001, 002, …, 366).
- `%m` - month as a zero-padded decimal number (01, 02, …, 12);
- `%w` - weekday as a decimal number, where 0 is Sunday and 6 is Saturday (0, 1, …, 6);
- `%y` - year without century as a zero-padded decimal number (00, 01, …, 99);
- `%Y` - year with century as a decimal number (0001, 0002, …, 2013, 2014, …, 9998, 9999);
- `%H` - hour (24-hour clock) as a zero-padded decimal number (00, 01, …, 23);
- `%I` - hour (12-hour clock) as a zero-padded decimal number (01, 02, …, 12);
- `%l` - hour (12-hour clock) as a decimal number (1, 2, …, 12);
- `%M` - minute as a zero-padded decimal number (00, 01, …, 59);
- `%p` - "AM" or "PM" according to the given time value;
- `%P` - like %p but in lowercase: "am" or "pm";
- `%S` - second as a zero-padded decimal number (00, 01, …, 59).

### Datetime Format Examples

Let's apply the format string to the date `Aug 6, 2019` and the time `4:46:35`:

```
%a  -->  "Tue"
%A  -->  "Tuesday"
%b  -->  "Aug"
%B  -->  "August"
%d  -->  "06"
%e  -->  "6"
%j  -->  "218"
%m  -->  "08"
%w  -->  "2" 
%y  -->  "19"
%Y  -->  "2019"
%H  -->  "04"
%I  -->  "04"
%l  -->  "4"
%M  -->  "46"
%P  -->  "am"
%p  -->  "AM"
%S  -->  "35"

%Y-%m-%dT%H:%M:%S      -->  "2019-08-06T04:46:35"
%m/%d/%Y               -->  "08/06/2019"
%m-%d-%Y %H:%M         -->  "08-06-2019 04:46"
%d.%m.%y               -->  "06.08.19"
%A, %b %e, %Y          -->  "Tuesday, Aug 6, 2019"
%b %d, %l:%M %p        -->  "Aug 06, 4:46 AM"
%B %Y                  -->  "August 2019"
%b %e, %Y              -->  "Aug 6, 2019"
%a, %e %b %Y %H:%M:%S  -->  "Tue, 6 Aug 2019 04:46:35"
%B %e %Y %H:%M %p      -->  "August 6 2019 04:46 AM"
```

## Exponent Format

The appearance of numbers in scientific notation can be further customized using the `exponentFormat` parameter of the [`theme()`](%api_theme%/theme/index.html) function:

- Scientific notation is used for numbers formatted with the `e` or `g` types.

- The `exponentFormat` parameter can take a string value:

  - `"e"` for e-notation (e.g. 1e+6);
  - `"pow_full"` for power-notation (e.g. ${ 1 \cdot 10^6 }$). This will enable superscript formatting for the exponent;
  - `"pow"` works as `"pow_full"` but will shorten powers of 10 (e.g. $10^6$ instead of ${ 1 \cdot 10^6 }$).

- Additionally, the `exponentFormat` parameter can be a tuple with three elements, where:

  - the first value specifies the appearance (`"e"`/`"pow"`/`"pow_full"`);
  - the second value sets the minimum exponent at which scientific notation starts being used (-7 by default);
  - the third value sets the maximum exponent at which scientific notation starts being used (6 by default).

  This only makes sense when the `g` type formatting is applied.

It can be summarized in the following table:

![Scientific notation table](formats_scientific_notation_table.png)

## Tooltip Customization

You can format text in tooltips, see: [Tooltip Customization](tooltips.md).

## Annotating Charts

You can format text in annotations, see: [Annotating Charts](annotations.md).

## Demo Notebooks

- [Formatting numbers](%nb-formatting%)
- [Formatting labels on plots](%nb-formatting_axes_etc%)
- [Text geoms](%nb-text_geoms%)
- [Exponent format in Lets-Plot](%nb-superscript_exponent%)