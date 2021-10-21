package andioopp.model.domain.damage;

/**
 * A functional interface for DamageFilters.
 */
@FunctionalInterface
public interface DamageFilter {
    boolean canBeDamagedBy(DamageSource src);
    default DamageFilter combineWith(DamageFilter other) {
        return (src) -> canBeDamagedBy(src) && other.canBeDamagedBy(src);
    }
}
