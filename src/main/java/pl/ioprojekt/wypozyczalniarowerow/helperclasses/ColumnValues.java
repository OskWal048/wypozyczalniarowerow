package pl.ioprojekt.wypozyczalniarowerow.helperclasses;

import java.util.List;

public class ColumnValues {

    private List<String> brands;

    private List<String> sizes;

    private List<String> colors;

    private List<String> types;

    private List<String> subtypes;

    private List<String> suspensions;

    public ColumnValues() {
    }

    public List<String> getBrands() {
        return brands;
    }

    public void setBrands(List<String> brands) {
        this.brands = brands;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public List<String> getSubtypes() {
        return subtypes;
    }

    public void setSubtypes(List<String> subtypes) {
        this.subtypes = subtypes;
    }

    public List<String> getSuspensions() {
        return suspensions;
    }

    public void setSuspensions(List<String> suspensions) {
        this.suspensions = suspensions;
    }

    public List<String> getSizes() {
        return sizes;
    }

    public void setSizes(List<String> sizes) {
        this.sizes = sizes;
    }
}
