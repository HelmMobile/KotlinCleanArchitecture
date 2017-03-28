package cat.helm.basearchitecture.data.repository.something

import cat.helm.basearchitecture.data.repository.datasource.ReadableDataSource
import cat.helm.basearchitecture.data.repository.query.Query

/**
 * Created by Borja on 21/3/17.
 */
class SomethingDataSource  constructor(override var queries: Set<Query<*>> = HashSet<Query<*>>()) : ReadableDataSource<String, String> {




}
