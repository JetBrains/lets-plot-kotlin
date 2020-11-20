# Tooltip Customization

- [Formatting tooltip fields](#formatting)
- [Customizing tooltip lines](#lines)
    - [Labels configuration](#labels-configuration)
- [Examples](#examples)
- [Outlier tooltips configuration](#outliers)
    - [Examples](#example-outliers)    
- [Hiding tooltips](#hiding-tooltips)
- [Example Notebooks](#example-notebooks)    
    
------
You can customize the content of tooltips for the layer by using the parameter `tooltips` of `geom` functions.

The following functions set lines and define formatting of the tooltip:

`tooltips=layer_tooltips().format(field, format).line(template)`


<a id="formatting"></a>
### Formatting tooltip fields: `layer_tooltips().format(field, format)`

Defines the format for displaying the value.
The format will be applied to the mapped value in the default tooltip or to the corresponding value specified in the `line` template.

#### Arguments

- `field` (string): The name of the variable/aesthetics.
The field name begins with `^` for aesthetics. You can specify variable names without a prefix, but the `@` prefix can be also used.
It's possible to set a format for all positional aesthetics: `^X` (all positional x) and `^Y` (all positional y).
For example:
    - `field = '^Y'` - for all positional y;
    - `field = '^y'` - for y aesthetic;
    - `field = 'y'` - for variable with the name "y".
    
- `format` (string): The format to apply to the field.
The format contains a number format (`'1.f'`) or a string template (`'{.1f}'`).
The numeric format for non-numeric value will be ignored. 
The string template contains “replacement fields” surrounded by curly braces `{}`. 
Any code that is not in the braces is considered literal text, and it will be copied unchanged to the result string. 
If you need to include a brace character into the literal text, it can be escaped by doubling: {{ and }}.
For example:
    - `.format('^color', '.1f')` -> `"17.0"`;
    - `.format('cty', '{.2f} (mpg)'))` -> `"17.00 (mpg)"`;
    - `.format('^color', '{{{.2f}}}')` -> `"{17.00}"`;
    - `.format('model', '{} {{text}}')` -> `"mustang {text}"`.

The string template in the `format` parameter will allow changing lines for the default tooltip without `line` specifying.

Variable's and aesthetic's formats are not interchangeable, for example, `var` format will not be applied to `aes` mapped to this variable.

<a id="lines"></a>
### Customizing tooltip lines: `layer_tooltips().line(template)`

Specifies the string template to use in a multi-line tooltip. If you add `line()`, it overrides the default tooltip.

Variables and aesthetics can be accessed via a special syntax:
- `^color` for aesthetic;
- `@year` for variable;
- `@{number of cylinders}` for a variable with spaces or non-word characters in the name;
- `@..count..` for statistics variables.

A '^' symbol can be escaped with a backslash; a brace character in the literal text - by doubling:
- `.line('text')` -> `"text"`;
- `.line('\^text')` -> `"^text"`;
- `.line('{{text}}')` -> `"{text}"`;
- `.line('@model')` -> `"mustang"`;
- `.line('{{@model}}')` -> `"{mustang}"`.

<a id="labels-configuration"></a>
#### Labels configuration
The default tooltip has a label before the value usually containing the name of the mapped variable.
It has its own behaviour similar to a blank label for an axis aesthetics. 
This default label can be set in the template by using a pair of symbols `@|`.
You can override the label by specifying a string value before `|` symbol.

Within the tooltip line, ou can align a label to left. The string formed by a template can be aligned to right.
If you do not specify a label, the string will be centered in the tooltip. For example:

- `line('^color')`: no label, value is centered;
- `line('|^color')`: label is empty, value is right-aligned;
- `line('@|^color')`: default label is used, value is right-aligned;
- `line('my label|^color')`: label is specified, value is right-aligned.


<a id="examples"></a>
### Examples

```
lets_plot(mpg) {x = "displ"; y = "cty"} + 
    geom_point(shape = 21, 
               color = "black",
               tooltips = layer_tooltips()
                          .format("cty", ".0f")
                          .format("hwy", ".0f")
                          .format("drv", "{}wd")
                          .line("@manufacturer @model")
                          .line("cty/hwy [mpg]|@cty/@hwy")
                          .line("@|@class")
                          .line("drive train|@drv")
                          .line("@|@year")) {fill = "drv"; size = "hwy"}
```
![](examples/images/tooltips_1.png)


Change format for the default tooltip:

```
lets_plot(mpg) {x = "displ"; y = "cty"} + 
    geom_point(shape = 21,
               color = "black",
               tooltips = layer_tooltips().format("^size", "{.0f} mpg")) {fill="drv"; size="hwy"}
```
![](examples/images/tooltips_2.png)




<a id="outliers"></a>
## Outlier tooltips configuration

The default an outlier's tooltip contains a string like `'name: value'`: there is no label and no alignment.
It's possible to change formatting of it with the `format` function. The number format (`'1.f'` ) leaves 
the string as is (`'name: value'`) and formats the value. The string template replaces the default string:
`‘{.1f}` - with `'value'`, `‘format text {.1f}’` - with `“format text value”`.

The specified `line` for an outlier will move it to a general multi-line tooltip.
   
<a id="example-outliers"></a>  
### Examples

`val p = lets_plot(mpg_dat) {x = "class"; y = "hwy"}` 


Change formatting for outliers:
```
p + geom_boxplot(tooltips = layer_tooltips()
                    .format("^Y", "{.0f}")       // all Y-positionals (note: no 'labels')
                    .format("^middle", ".2f")    // different precision for 'middle' (note: default 'label')
                    .format("^ymin", "min: {}")  // ymin/ymax aesthetics:
                    .format("^ymax", "max: {}")) //  - add custom 'label'
```                        
![](examples/images/tooltips_3.png)

                  
Move outliers to a general tooltip:

```
p + geom_boxplot(tooltips = layer_tooltips()
                    .format("^Y", ".0f")
                    .format("^middle", ".2f")
                    .line("min/max|^ymin/^ymax")
                    .line("lower/upper|^lower/^upper")
                    .line("@|^middle"))
```
![](examples/images/tooltips_4.png)
                 


<a id="hiding-tooltips"></a> 
## Hiding tooltips     
Set `tooltips = tooltips_none` to hide tooltips from the layer.
          
          
<a id="example-notebooks"></a> 
## Example Notebooks
 
* [tooltip_config.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks-dev/tooltip_config.ipynb)
